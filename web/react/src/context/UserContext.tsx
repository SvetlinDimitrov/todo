import {createContext, ReactNode, useState} from 'react';
import User from "../model/interfaces/objects/User.ts";

interface UserProviderProps {
  children: ReactNode;
}

interface UserContextType {
  user: User;
  setUser: (user: User) => void;
  auth: string;
}

export const UserContext = createContext<UserContextType | undefined>(undefined);

export const UserProvider = ({children}: UserProviderProps) => {
  const [user, setUser] = useState<User>({
    email: 'test@abv.bg',
    password: '12345'
  });

  const auth = btoa(`${user.email}:${user.password}`);

  return (
    <UserContext.Provider value={{user, setUser , auth}}>
      {children}
    </UserContext.Provider>
  );
};
