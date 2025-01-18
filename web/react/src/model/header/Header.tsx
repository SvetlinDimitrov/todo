import {Link, useParams} from 'react-router-dom';
import styles from './Header.module.css';
import {useContext} from "react";
import {UserContext} from "../../context/UserContext.tsx";

const Header = () => {
  const {id , taskId} = useParams();
  const userContext = useContext(UserContext);

  return (
    <header className={styles.header}>
      <nav className={styles.nav}>
        <div className={styles.logoContainer}>
          <img src="https://www.pngall.com/wp-content/uploads/5/Profile.png" alt="Your Name"
               className={styles.profilePic}/>
          <p className={styles.profileName}>{userContext?.user.email}</p>
        </div>
        {taskId && <Link to={`/project/${id}`} className={styles.homeLink}>Project</Link>}
        <Link to={`/`} className={styles.homeLink}>Home</Link>
      </nav>
    </header>
  )
};

export default Header;