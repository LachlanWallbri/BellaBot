import React from "react";
import { Dispatch, SetStateAction, createContext, useState } from "react";
import { screenType } from "../../utils/types";

type ScreentContextType = {
  currentScreen: screenType,
  setCurrentScreen: Dispatch<SetStateAction<screenType>>,
  prevScreen: screenType,
  setPrevScreen: Dispatch<SetStateAction<screenType>>
}

export const ScreenContext = createContext<ScreentContextType | undefined>(undefined);

// Keeping track of the current screen that is being shown.
const ScreenContextComponent = ({ children }): JSX.Element => {
  const [currentScreen, setCurrentScreen] = useState<screenType>("LOGIN");
  const [prevScreen, setPrevScreen] = useState<screenType>("LOGIN");


  return (
    <ScreenContext.Provider value={{ currentScreen, setCurrentScreen, prevScreen, setPrevScreen }}>
      {children}
    </ScreenContext.Provider>);
};

export default ScreenContextComponent;