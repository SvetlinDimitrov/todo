import style from "./TaskForm.module.css";
import React, {useState} from "react";
import Task from "../interfaces/objects/Task.ts";

interface TaskFormProps {
  headerText: string;
  handleAction: (task : Task) => void;
  buttonText: string;
  handleClose: () => void;
  task: Task | null;
}

function TaskForm({headerText, handleAction, buttonText, handleClose, task}: TaskFormProps) {
  const [description, setDescription] = useState(task?.description ?? "");
  const [name, setName] = useState(task?.name ?? "");
  const [done, setDone] = useState(task?.done ?? false);

  const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setName(e.target.value);
  };

  const handleDescriptionChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDescription(e.target.value);
  };

  const handleCheckboxChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDone(e.target.checked);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (name.length >= 3) {
      handleAction({
          id: task?.id ?? null,
          name,
          description,
          done,
          projectId: task?.projectId ?? null
        }
      );
    }
  };

  return (
    <div className={style.addTaskModal}>
      <form onSubmit={handleSubmit} className={style.addTaskForm}>
        <button className={style.closeButton} onClick={handleClose}>×</button>
        <h2 className={style.header}>{headerText}</h2>
        <input
          type="text"
          value={name}
          onChange={handleNameChange}
          className={`${style.taskInput} ${name.length < 3 ? style.invalid : ''}`}
          placeholder="Task Name"
        />
        <input
          type="text"
          value={description}
          onChange={handleDescriptionChange}
          className={style.taskInput}
          placeholder="Task Description"
        />
        <label className={style.checkboxLabel}>
          <input
            type="checkbox"
            checked={done}
            onChange={handleCheckboxChange}
          /> Done
        </label>
        {name.length < 3 && <p className={style.error}>Name must be at least 3 characters long</p>}
        <button className={style.button} type="submit">{buttonText}</button>
      </form>
    </div>
  );
}

export default TaskForm;