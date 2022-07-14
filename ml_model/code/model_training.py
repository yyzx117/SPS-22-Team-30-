import cv2
import keras
import tqdm
import numpy as np
import os
import pickle
import random
import tensorflow as tf

DATA_DIR = './asl-alphabet/asl_alphabet_train/asl_alphabet_train'
TEST_DIR = './asl-alphabet/asl_alphabet_test'   
letters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','nothing','space']
IMG_SIZE = 300

def getTrainingData():
    if(os.path.isfile('image_data.pickle')) :
        image_data = pickle.load(open('image_data.pickle','rb'))
        image_classification = pickle.load(open('image_classification.pickle','rb'))
        return image_data , image_classification
    else:
        tdata = []
        # Read all the image data and add it and its letter to tdata array
        for letter in letters:
            path = os.path.join(DATA_DIR,letter)
            class_num = letters.index(letter)

            for img in tqdm.tqdm(os.listdir(path)):  
                try:
                    img_array = cv2.imread(os.path.join(path,img) , cv2.IMREAD_GRAYSCALE)  
                    new_img_array = cv2.resize(img_array, (IMG_SIZE, IMG_SIZE)) 
                    tdata.append([new_img_array, class_num]) 
                except Exception as e: 
                    pass

        # shuffle the data
        random.shuffle(tdata)

        # store data in arrays
        image_data = []
        image_classification = []

        for data,classification in tdata:
            image_data.append(data)
            image_classification.append(classification)
        
        #model needs an numpy array
        image_data = np.array(image_data).reshape(-1, IMG_SIZE, IMG_SIZE, 1)

        #save image data so next time, its much easier to read
        pickle_out = open("image_data.pickle","wb")
        pickle.dump(image_data, pickle_out)
        pickle_out.close()
        print("finished saving image_data")
        
        pickle_out = open("image_classification.pickle","wb")
        pickle.dump(image_classification, pickle_out)
        pickle_out.close()
        print("finished saving image_classification")
        return image_data, image_classification

def getTestData():
    if(os.path.isfile('image_test_data.pickle')) :
        image_test_data = pickle.load(open('image_test_data.pickle','rb'))
        image_test_classification = pickle.load(open('image_test_classification.pickle','rb'))
        return image_test_data , image_test_classification
    else:
        tdata = []
        # Read all the image data and add it and its letter to tdata array
        for letter in letters:
            class_num = letters.index(letter)
            img_name = letter + "_test.jpg"
            try:
                img_array = cv2.imread(os.path.join(TEST_DIR, img_name) , cv2.IMREAD_GRAYSCALE)  
                new_img_array = cv2.resize(img_array, (IMG_SIZE, IMG_SIZE)) 
                tdata.append([new_img_array, class_num]) 
            except Exception as e: 
                print(e)
                pass

        # shuffle the data
        random.shuffle(tdata)

        # store data in arrays
        image_test_data = []
        image_test_classification = []

        for data,classification in tdata:
            image_test_data.append(data)
            image_test_classification.append(classification)
        
        #model needs an numpy array
        image_test_data = np.array(image_test_data).reshape(-1, IMG_SIZE, IMG_SIZE, 1)

        #save image data so next time, its much easier to read
        pickle_out = open("image_test_data.pickle","wb")
        pickle.dump(image_test_data, pickle_out)
        pickle_out.close()
        print("finished saving image_test_data")
        
        pickle_out = open("image_test_classification.pickle","wb")
        pickle.dump(image_test_classification, pickle_out)
        pickle_out.close()
        print("finished saving image_test_classification")
        return image_test_data, image_test_classification

def buildModel(image_data , image_classification, image_test_data, image_test_classification):

    # # #normalize the data
    # image_data = image_data/255.0 
    # print("Normalized the image data")

    #create model object
    model= keras.models.Sequential()

    #create the Convolutional layer
    model.add(keras.layers.Conv2D(64, (3,3), input_shape= image_data.shape[1:]))
    model.add(keras.layers.Activation('relu'))
    model.add(keras.layers.MaxPooling2D(pool_size=(3, 3)))

    model.add(keras.layers.Conv2D(128, (3,3)))
    model.add(keras.layers.Activation('relu'))
    model.add(keras.layers.MaxPooling2D(pool_size=(3, 3)))

    model.add(keras.layers.Conv2D(256, (3,3)))
    model.add(keras.layers.Activation('relu'))
    model.add(keras.layers.MaxPooling2D(pool_size=(3, 3)))

    model.add(keras.layers.Conv2D(256, (3,3)))
    model.add(keras.layers.Activation('relu'))
    model.add(keras.layers.MaxPooling2D(pool_size=(3, 3)))

    model.add(keras.layers.BatchNormalization())
    #flatten the model
    model.add(keras.layers.Flatten())

    # 

    model.add(keras.layers.Dense(64))
    model.add(keras.layers.Activation('relu'))

    model.add(keras.layers.Dense(28))
    model.add(keras.layers.Activation('softmax'))

    print("Layers have been added to CNN")

    #compile the model
    model.compile(loss='sparse_categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy'])


    #train model
    model.fit(image_data, image_classification, batch_size=32, epochs=15, validation_data=(image_test_data, image_test_classification))
    print("CNN Model has been trained")

    #save the model
    model.save("alphabet_trained_model.h5")
    print("CNN Model has been saved")



image_data , image_classification = getTrainingData()
print("Have secussfully retrived the image data and classification")
image_test_data, image_test_classification = getTestData()
print("Have secussfully retrived the test image data and classification")

image_data = np.array(image_data)
image_classification = np.array(image_classification)
image_test_data = np.array(image_test_data)
image_test_classification = np.array(image_test_classification)

buildModel(image_data , image_classification, image_test_data, image_test_classification)