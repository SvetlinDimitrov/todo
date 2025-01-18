import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './reset.css'
import {UserProvider} from "./context/UserContext.tsx";
import {LoadingProvider} from "./context/LoadingContext.tsx";

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <UserProvider>
      <LoadingProvider>
        <App/>
      </LoadingProvider>
    </UserProvider>
  </React.StrictMode>,
)
