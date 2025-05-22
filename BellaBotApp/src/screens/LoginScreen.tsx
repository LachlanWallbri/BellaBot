import React, { useContext, useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet, KeyboardAvoidingView } from 'react-native';
import CryptoJS from 'crypto-js';
import { ScreenContext } from '../components/contexts/ScreenContextComponent';
import { BELLABOT_BLUE } from '../utils/constants';
import config from "../../config/config.json";

const LoginScreen = (): JSX.Element => {
  const { setCurrentScreen } = useContext(ScreenContext);
  const [username, setUsername] = useState<string>('');
  const [password, setPassword] = useState<string>('');

  const handleLogin = () => {
    if (CryptoJS.SHA256(username).toString(CryptoJS.enc.Hex) === config.username
      && CryptoJS.SHA256(password).toString(CryptoJS.enc.Hex) === config.password) {
      setCurrentScreen("TEXT");
    } else {
      alert('Invalid username or password');
    }
  };

  return (
    <KeyboardAvoidingView
      style={styles.container}
      behavior={"height"}
      testID='login-form'
    >
      <View style={styles.innerContainer}>
        <View style={styles.card}>
          <View style={styles.titleContainer}>
            <Text testID='login-text' style={styles.title}>BellaBotApp</Text>
          </View>
          <View style={styles.inputContainer}>
            <TextInput
              style={styles.input}
              placeholder="Username"
              value={username}
              onChangeText={setUsername}
              testID='input-username'
            />
            <TextInput
              style={styles.input}
              placeholder="Password"
              secureTextEntry
              value={password}
              onChangeText={setPassword}
              testID='input-password'
            />
            <View style={styles.outerButtonContainer}>
              <View style={styles.innerButtonContainer}>
                <Button testID='login-btn' title="Login" color={BELLABOT_BLUE} onPress={handleLogin} />
              </View>
            </View>
          </View>
        </View>
      </View>
    </KeyboardAvoidingView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  innerContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
  },
  card: {
    width: '80%',
    paddingVertical: 40,
    paddingHorizontal: 20,
    backgroundColor: '#fff',
    borderRadius: 10,
    elevation: 5,
    alignItems: 'center',
  },
  titleContainer: {
    justifyContent: 'center',
    marginBottom: 20,
  },
  inputContainer: {
    width: '100%',
  },
  title: {
    fontSize: 28,
    textAlign: 'center',
    fontFamily: "Outfit-Regular"
  },
  input: {
    width: '100%',
    height: 50,
    borderColor: 'gray',
    borderWidth: 1,
    borderRadius: 5,
    marginBottom: 20,
    paddingHorizontal: 10,
    fontSize: 18,
    fontFamily: "Outfit-Regular"
  },
  outerButtonContainer: {
    alignItems: "center",
    marginTop: 10,
    width: '100%',
  },
  innerButtonContainer: {
    marginTop: 20,
    width: '90%',
  },
});

export default LoginScreen;
