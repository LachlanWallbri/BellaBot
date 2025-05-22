import React from 'react';
import { View, StyleSheet, Image, StatusBar } from 'react-native';

import angryFace from '../../assets/faces/angry.webp';
import cuteFace from '../../assets/faces/cute.webp';
import defaultFace from '../../assets/faces/default.webp';
import dizzyFace from '../../assets/faces/dizzy.webp';
import happyFace from '../../assets/faces/happy.webp';
import sleepyFace from '../../assets/faces/sleepy.webp';
import winkFace from '../../assets/faces/wink.webp';

const faceFiles = [angryFace, cuteFace, defaultFace, dizzyFace, happyFace, sleepyFace, winkFace];

const FaceScreen = (): JSX.Element => {

  // We effectively choose a random face to show, and make it take up the entire screen
  const chosenFaceIndex = Math.floor(Math.random() * faceFiles.length);
  const chosenFace = faceFiles[chosenFaceIndex];

  return (
    <View testID='face-root' style={styles.container}>
      <StatusBar hidden={true} />
      <Image
        source={chosenFace}
        style={styles.image}
        resizeMode="cover"
        testID='face-image'
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  image: {
    flex: 1,
    width: '100%',
    height: '100%',
  },
});

export default FaceScreen;
