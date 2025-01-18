import style from "./ProjectForm.module.css";
import React, {useState} from "react";
import Project from "../interfaces/objects/Project.ts";

interface ProjectFormProps {
  headerText: string;
  buttonText: string;
  handleAction: (name: string) => void;
  handleClose: () => void;
  project: Project;
}

function ProjectForm({ headerText, handleAction ,buttonText , handleClose , project} : ProjectFormProps) {

  const [isValid, setIsValid] = useState(true);
  const [projectName, setProjectName] = useState(project.name ?? "");

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setIsValid(e.target.value.length >= 3);
    setProjectName(e.target.value);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (projectName.length >= 3) {
      handleAction(projectName);
    } else {
      setIsValid(false);
    }
  };

  return (
    <div className={style.addProjectModal}>
      <form onSubmit={handleSubmit} className={style.addProjectForm}>
        <button
          className={style.closeButton}
          onClick={handleClose}>×</button>
        <h2 className={style.header}>{headerText}</h2>
        <input
          type="text"
          value={projectName}
          onChange={handleInputChange}
          className={`${style.projectNameInput} ${!isValid ? style.invalid : ''}`}
          placeholder="Project Name"
        />
        {!isValid && <p className={style.error}>Project name must be at least 3 characters long</p>}
        <button className={style.button} type="submit">{buttonText}</button>
      </form>
    </div>
  );
}

export default ProjectForm;