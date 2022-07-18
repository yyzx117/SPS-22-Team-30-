package com.google.sps.servlets;

import org.datavec.image.loader.ImageLoader;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
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
        String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z", "nothing", "space" };

        /*
         * // load the model
         * String simpleMlp = new ClassPathResource(
         * "./ml-model/alphabet_trained_model.h5").getFile().getPath();
         * MultiLayerNetwork model;
         * try {
         * model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
         * 
         * // Read and gray out the image
         * String imgname = request.getParameter("formData");
         * BufferedImage grayedImage = grayscale(imgname);
         * 
         * //Resize the image
         * BufferedImage resizedImage = resizeImage(grayedImage, 300, 300);
         * 
         * ImageLoader loader = new ImageLoader();
         * INDArray imgArr = loader.asMatrix(resizedImage).reshape(-1, 300, 300);
         * 
         * // get the prediction
         * int prediction = (int) model.output(imgArr).getDouble(0);
         * String letterPredicted = alphabet[prediction];
         * 
         * response.setContentType("text/html;");
         * response.getWriter().println("Translated to:" + imgname);
         * System.out.println(imgname);
         * } catch (InvalidKerasConfigurationException |
         * UnsupportedKerasConfigurationException e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * }
         */
        String imgname = request.getParameter("formData");
        response.setContentType("text/html");
        response.getWriter().println("x");
    }

    // Take in the image and gray it out Link on how to gray scale an image:
    // https://dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-grayscale-image-in-java
    private BufferedImage grayscale(String resimg) throws IOException {
        File f = new File(resimg);
        BufferedImage img = ImageIO.read(f);

        // get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        // Grey each pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // calculate average
                int avg = (r + g + b) / 3;

                // replace RGB value with avg
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                img.setRGB(x, y, p);
            }
        }
        // write image
        f = new File("SPS-22-Team-30-/src/main/webapp/Results/greyscale.jpg");
        ImageIO.write(img, "jpg", f);
        return img;
    }
    // class ends here

    // Resizeing the image in java to have a height and width of 300:
    // https://www.baeldung.com/java-resize-image
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
            throws IOException {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }

    // I'm not sure but you may need to run a reshape operation similar to that in
    // python to get the model to work: This is what the reshape function does:
    // https://note.nkmk.me/en/python-numpy-reshape-usage/

}