import styles from './NotFound.module.css';
import Header from "../../model/header/Header.tsx";

const NotFound = () => {
  return (
    <>
      <Header/>
      <div className={styles.notFoundContainer}>
        <h1>404 Not Found</h1>
        <p>The page you are looking for does not exist.</p>
      </div>
    </>
  );
};

export default NotFound;