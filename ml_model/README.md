The Dataset for this model was retrived from: https://www.kaggle.com/datasets/grassknoted/asl-alphabet

The way I tested the model can be found in `./code/main_frame.py`

To use this model follow the directions provided here in how to load it: https://towardsdatascience.com/deploying-keras-deep-learning-models-with-java-62d80464f34a


```
<dependencies>
  <dependency>      
    <groupId>org.deeplearning4j</groupId>      
    <artifactId>deeplearning4j-core</artifactId>
    <version>1.0.0-beta2</version>    
  </dependency>         
  <dependency>      
    <groupId>org.deeplearning4j</groupId>      
    <artifactId>deeplearning4j-modelimport</artifactId>      
    <version>1.0.0-beta2</version>    
  </dependency>                       
  <dependency>      
    <groupId>org.nd4j</groupId>      
    <artifactId>nd4j-native-platform</artifactId>
    <version>1.0.0-beta2</version>    
  </dependency>
  <dependency>      
    <groupId>org.eclipse.jetty</groupId>      
    <artifactId>jetty-server</artifactId>      
    <version>9.4.9.v20180320</version>   
  </dependency>  <dependency>      
    <groupId>com.google.cloud.dataflow</groupId>      
    <artifactId>google-cloud-dataflow-java-sdk-all</artifactId>  
    <version>2.2.0</version>     
  </dependency>
</dependencies>
```

Then in your java code you would load the model like:

```
// imports
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;

// prepopulate the possible results
string[] alphabet = letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z","nothing","space"};


// load the model
String simpleMlp = new ClassPathResource(
                          "alphabet_trained_model.h5").getFile().getPath();
MultiLayerNetwork model = KerasModelImport.
                    importKerasSequentialModelAndWeights(simpleMlp);

// Take in the image and gray it out
  Link on how to gray scale an image: https://dyclassroom.com/image-processing-project/how-to-convert-a-color-image-into-grayscale-image-in-java

// Resizeing the image in java to have a height and width of 300:
  https://www.baeldung.com/java-resize-image

// I'm not sure but you may need to run a reshape operation similar to that in python to get the model to work:
  This is what the reshape function does: https://note.nkmk.me/en/python-numpy-reshape-usage/

// get the prediction
int prediction = (int)model.output(features).getDouble(0);
String letterPredicted = alphabet[prediction] ;
```

This is my python code for how I tested the model:

```
print("The letter is supposed to be: " + img)
img_array = cv2.imread(os.path.join(DATA_DIR,img) , cv2.IMREAD_GRAYSCALE)  
image = cv2.resize(img_array, IMG_SIZE)
image = image.reshape(-1,300,300)

prediction = self.model.predict([image])
pridictionString = "The Letter is: " + self.letters[np.argmax(prediction[0])]
print(pridictionString)
```