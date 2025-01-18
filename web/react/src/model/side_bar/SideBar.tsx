import styles from './SideBar.module.css';
import SideBarHeader from "./header/SideBarHeader.tsx";
import ProjectSection from "./project/ProjectSection.tsx";
import Project from "../interfaces/objects/Project.ts";

interface SideBarProps {
  handleAddClick: () => void;
  projects: Project[] ;
}


function SideBar({handleAddClick , projects}: SideBarProps) {

  return (
    <div className={styles.sideBar}>
      <SideBarHeader/>
      <div className={styles.divider}></div>
      <ProjectSection
        handleAddClick={handleAddClick}
        projects={projects}
      />
    </div>
  );
}

export default SideBar;