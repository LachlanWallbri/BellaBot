import React, { useContext, useEffect, useRef } from 'react';
import { View, StyleSheet, TextInput, Keyboard } from 'react-native';
import NavBar from '../components/NavBar';
import { TextContext } from '../components/contexts/TextContextComponent';

const TextScreen = (): JSX.Element => {
  const { text, setText } = useContext(TextContext);

  const textInputRef = useRef(null);

  useEffect(() => {
    const keyboardDidHideListener = Keyboard.addListener('keyboardDidHide', handleKeyboardDidHide);

    return () => {
      keyboardDidHideListener.remove();
    };
  }, []);

  const handleKeyboardDidHide = () => {
    console.log('sup');
    if (textInputRef.current) {
      textInputRef.current.blur();
    }
  };

  return (
    <View style={styles.container}>
      <NavBar />
      <TextInput
        style={styles.content}
        multiline
        onChangeText={setText}
        value={text}
        textAlignVertical="top"
        testID='text-input'
        ref={textInputRef}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
  },
  content: {
    display: "flex",
    flexGrow: 1,
    backgroundColor: '#fff',
    padding: 24,
    borderWidth: 1,
    fontSize: 24,
    borderColor: '#ccc',
    color: '#000',
    borderRadius: 15,
    fontFamily: "Outfit-Regular"
  },

});

export default TextScreen;
