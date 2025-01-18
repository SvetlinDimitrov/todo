import styles from "./ProjectSection.module.css";
import Project from "../../interfaces/objects/Project.ts";
import {useNavigate} from "react-router-dom";

interface ProjectSectionProps {
  handleAddClick: () => void;
  projects: Project[];
}

function ProjectSection({handleAddClick, projects}: ProjectSectionProps) {

  const navigate = useNavigate();

  const handleClick = (projectId : number) => {
    navigate(`/project/${projectId}`);
  };

  return (
    <>
      <div className={styles.projectsHeader}>
        <h2 className={styles.projectsTitle}>Projects</h2>
        <div className={styles.addProject} onClick={handleAddClick}>+</div>
      </div>
      <ul className={styles.projectsList}>
        {projects.map((project: Project) => (
          <li
            key={project.id}
            onClick={() => handleClick(project.id)}
          >{project.name}</li>
        ))}
      </ul>
    </>
  )
}

export default ProjectSection;