import React from 'react';
import Slider from '@react-native-community/slider';
import { StyleSheet } from 'react-native';

type BellaBotSliderProps = {
  min: number,
  max: number,
  step: number,
  value: number,
  onChange: (e) => void
  testID: string
}

// A wrapper for the slider component to allow for easy resuse
const BellaBotSlider = ({ min, max, step, value, onChange, testID }: BellaBotSliderProps): JSX.Element => {
  return (
    <Slider
      step={step}
      maximumValue={max}
      minimumValue={min}
      style={styles.slider}
      value={value}
      onSlidingComplete={e => onChange(e)}
      thumbTintColor={viewPositionSlider.thumbColor}
      maximumTrackTintColor={viewPositionSlider.trackColor}
      minimumTrackTintColor={viewPositionSlider.trackColor}
      testID={testID}
    />
  );
};

const viewPositionSlider = {
  trackColor: '#fff',
  thumbColor: '#fff',

};

const styles = StyleSheet.create({
  slider: {
    width: '80%',
  }
});

export default BellaBotSlider;