import styles from "./ProjectHeader.module.css";
import Project from "../../../model/interfaces/objects/Project.ts";

interface ProjectHeaderProps {
  handleTickClick: () => void;
  handleAddClick: () => void;
  project: Project | null;
}

function ProjectHeader({handleTickClick, handleAddClick, project}: ProjectHeaderProps) {
  return (
    <div className={styles.headerContainer}>
      <h1 className={styles.projectName}>{project?.name ?? "Undefined"}</h1>
      {(project?.tasks.length === 0 || project?.tasks.every(t => t.done)) && (
        <button className={styles.tickButton} onClick={handleTickClick}>x</button>
      )}
      <button className={styles.addButton} onClick={handleAddClick}>Add Task</button>
    </div>
  )
}

export default ProjectHeader;