import React from "react";
import { createContext, useState } from "react";
import { DEFAULT_TEXT_SCREEN_TEXT } from "../../utils/constants";

export const TextContext = createContext(undefined);

// Keeping the current text on the text screen throughout
// the lifetime of the application
const TextContextComponent = ({ children }): JSX.Element => {
  const [text, setText] = useState(DEFAULT_TEXT_SCREEN_TEXT);

  return (
    <TextContext.Provider value={{ text, setText }}>
      {children}
    </TextContext.Provider>
  )
}

export default TextContextComponent;