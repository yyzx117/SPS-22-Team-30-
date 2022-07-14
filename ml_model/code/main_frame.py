import cv2
import keras   
import numpy as np
import tqdm
import os

class MainFrame:
    def __init__(self, model):
        self.letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','nothing','space']

        self.model = model
        DATA_DIR = './asl-alphabet/asl_alphabet_test'   
        IMG_SIZE = 300,300      
        
        for img in tqdm.tqdm(os.listdir(DATA_DIR)):  
            try:
                print("The letter is supposed to be: " + img)
                img_array = cv2.imread(os.path.join(DATA_DIR,img) , cv2.IMREAD_GRAYSCALE)  
                image = cv2.resize(img_array, IMG_SIZE)
                image = image.reshape(-1,300,300)

                prediction = self.model.predict([image])
                pridictionString = "The Letter is: " + self.letters[np.argmax(prediction[0])]
                print(pridictionString)
            except Exception as e:
                print(e)
                pass

def main():
    try:
        model = keras.models.load_model("../alphabet_trained_model.h5")
    except:
        model = None
    
    MainFrame(model)

if __name__ == "__main__":
    main()