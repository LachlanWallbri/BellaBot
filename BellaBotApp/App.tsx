import React, { useContext } from 'react';
import { SafeAreaView } from 'react-native';
import TextScreen from './src/screens/TextScreen';
import LoginScreen from './src/screens/LoginScreen';
import FaceScreen from './src/screens/FaceScreen';
import StatsScreen from './src/screens/StatsScreen';
import GraphsScreen from './src/screens/GraphsScreen';
import TextContextComponent from './src/components/contexts/TextContextComponent';
import { GestureHandlerRootView, TapGestureHandler } from 'react-native-gesture-handler';
import DataContextComponent from './src/components/contexts/DataContextComponent';
import ScreenContextComponent, { ScreenContext } from './src/components/contexts/ScreenContextComponent';

const App = () => {
  const { currentScreen, setCurrentScreen, prevScreen, setPrevScreen } = useContext(ScreenContext);

  // For changing to the face and back
  const onDoubleTap = () => {
    if (currentScreen === 'LOGIN') {
      return;
    }

    if (currentScreen !== 'FACE') {
      setPrevScreen(currentScreen);
      setCurrentScreen('FACE');
    } else {
      setCurrentScreen(prevScreen);
    }
  };

  return (
    <GestureHandlerRootView style={{ flex: 1 }}>
      <TapGestureHandler numberOfTaps={2} onActivated={onDoubleTap}>
        <SafeAreaView style={{ flex: 1 }}>
          <TextContextComponent>
            <DataContextComponent>
              {currentScreen === 'LOGIN' && <LoginScreen />}
              {currentScreen === 'TEXT' && <TextScreen />}
              {currentScreen === 'FACE' && <FaceScreen />}
              {currentScreen === 'STATS' && <StatsScreen />}
              {currentScreen === 'GRAPHS' && <GraphsScreen />}
            </DataContextComponent>
          </TextContextComponent>
        </SafeAreaView>
      </TapGestureHandler>
    </GestureHandlerRootView>
  );
}

// The actual entry point for the application.
// Simply a wrapper so that screen context can be used within the above
// App
const AppWrapper = () => {
  return (
    <ScreenContextComponent>
      <App />
    </ScreenContextComponent>
  )

}

export default AppWrapper;