import styles from "./SideBarHeader.module.css";

function SideBarHeader() {

  return (
    <>
      <img src="https://www.pngall.com/wp-content/uploads/5/Profile.png"
           alt="Your Name"
           className={styles.profilePic}/>
      <p className={styles.profileName}>test1@abv.bg</p>
    </>
  );
}

export default SideBarHeader;