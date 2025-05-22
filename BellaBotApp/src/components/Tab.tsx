import React from 'react';
import { Pressable, Text, StyleSheet } from 'react-native';
import { BELLABOT_BLUE } from '../utils/constants';

type TabProps = {
  title: string,
  testID: string,
  isActive: boolean,
  onPress: () => void
}


const Tab = ({ title, testID, isActive, onPress }: TabProps): JSX.Element => (
  <Pressable
    testID={testID}
    style={[styles.tab, isActive && styles.activeTab]}
    onPress={onPress}
  >
    <Text style={[styles.tabText, isActive ? styles.activeTabTitle : styles.tabTitle]}>{title}</Text>
  </Pressable>
);


const styles = StyleSheet.create({
  tabTitle: {
    color: 'white',
  },
  activeTabTitle: {
    color: BELLABOT_BLUE,
    fontWeight: 'bold',
  },
  tab: {
    padding: 8,
    backgroundColor: BELLABOT_BLUE,
  },
  activeTab: {
    padding: 8,
    backgroundColor: 'white',
    borderRadius: 15,
  },
  tabText: {
    fontSize: 18,
    fontWeight: "bold",
    fontFamily: "Outfit-Regular"
  }
});

export default Tab;

