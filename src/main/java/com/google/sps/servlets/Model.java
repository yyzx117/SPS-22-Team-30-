package com.google.sps.servlets;

import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/model")
public final class Model extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // prepopulate the possible results
        String[] alphabet = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z"};

        // load the model
        String simpleMlp = new ClassPathResource("com/google/sps/servlets/ml-model/asl_model_better.h5").getFile().getPath();
        MultiLayerNetwork model;
        try {
            model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
            
            // Read the image
            String[] parameterLines = request.getReader().lines().collect(Collectors.joining(System.lineSeparator())).split("\n");
            String imgData = parameterLines[3];
            String base64Image = imgData.split(",")[1];
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));

            // Gray out the imgae
            //BufferedImage grayedImage = grayscale(img);
            
            //Resize the image
            BufferedImage resizedImage = resizeImage(img, 200, 200);
            
            NativeImageLoader loader = new NativeImageLoader(resizedImage.getHeight(), resizedImage.getWidth(), 3);
            ImagePreProcessingScaler imageScaler = new ImagePreProcessingScaler();

            INDArray imgMatrix = loader.asMatrix(resizedImage);
            INDArray reshaped = imgMatrix.reshape(1, 3, 200, 200);
    
            imageScaler.transform(reshaped);
            
            // get the prediction
            int[] prediction = model.predict(reshaped);
            String letterPredicted = alphabet[prediction[0]];

            response.setContentType("text/html;");
            response.getWriter().println("Translated to:" + letterPredicted);
            // System.out.println(imgname);
        } catch (InvalidKerasConfigurationException |
            UnsupportedKerasConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Resizeing the image in java to have a height and width of 300:
    // https://www.baeldung.com/java-resize-image
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
            throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 1, 3, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    // I'm not sure but you may need to run a reshape operation similar to that in
    // python to get the model to work: This is what the reshape function does:
    // https://note.nkmk.me/en/python-numpy-reshape-usage/

}