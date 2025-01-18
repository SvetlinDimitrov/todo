import React, {createContext, useState} from 'react';
import Loader from "../model/loader/Loader.tsx";

interface LoaderContextProps {
  children: React.ReactNode;
}

interface LoadingContextType {
  setLoading: React.Dispatch<React.SetStateAction<boolean>>;
}

const LoadingContext = createContext<LoadingContextType>({
  setLoading: () => {
  }
});

const LoadingProvider = ({children}: LoaderContextProps) => {
  const [isLoading, setLoading] = useState(false);

  return (
    <LoadingContext.Provider value={{setLoading}}>
      {isLoading && (
        <Loader/>
      )}
      {children}
    </LoadingContext.Provider>
  );
};

export {LoadingContext, LoadingProvider};