import React, { useContext } from 'react';
import { View, StyleSheet } from 'react-native';
import { TextContext } from './contexts/TextContextComponent';
import { screenType } from '../utils/types';
import { BELLABOT_BLUE, DEFAULT_TEXT_SCREEN_TEXT } from '../utils/constants';
import Tab from '../components/Tab';
import { ScreenContext } from './contexts/ScreenContextComponent';

type TabType = {
  title: screenType
  screen: screenType
}

const NavBar = (): JSX.Element => {
  const { setText } = useContext(TextContext);
  const { currentScreen, setCurrentScreen } = useContext(ScreenContext);

  const handleScreenTransition = (newScreen: screenType) => {
    setCurrentScreen(newScreen);

    // If we are on the login page, reset the TEXTPAGE text
    if (newScreen === "LOGIN") {
      setText(DEFAULT_TEXT_SCREEN_TEXT);
    }
  };

  // To add another tab, add an entry here.
  // title is the text in the tab
  // screen is the screen you want to direct to
  const tabs: TabType[] = [
    { title: 'STATS', screen: "STATS" },
    { title: 'GRAPHS', screen: "GRAPHS" },
    { title: 'TEXT', screen: "TEXT" },
    { title: 'LOGOUT', screen: "LOGIN" },
  ];

  return (
    <View testID='nav-bar' style={styles.tabs}>
      {tabs.map(tab => (
        <Tab
          key={tab.title}
          title={tab.title}
          testID={'tab-' + tab.title.toLowerCase()}
          isActive={currentScreen === tab.title}
          onPress={() => handleScreenTransition(tab.screen)}
        />
      ))}
    </View>
  );
};

const styles = StyleSheet.create({
  tabs: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 16,
    backgroundColor: BELLABOT_BLUE,
    paddingVertical: 8,
    paddingHorizontal: 60,
    borderRadius: 15
  },
});

export default NavBar;
