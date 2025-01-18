import {BallTriangle} from "react-loader-spinner";
import styles from './Loader.module.css';

function Loader() {
  return (
    <div className={styles.loaderContainer}>
      <BallTriangle color="#00BFFF" height={80} width={80}/>
    </div>
  );
}
export default Loader;