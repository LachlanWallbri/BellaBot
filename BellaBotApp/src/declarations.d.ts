/* eslint-disable @typescript-eslint/no-explicit-any */
// This file is so that we can import the webp files without having to use require(...)


declare module '*.webp' {
  const value: any;
  export default value;
}
