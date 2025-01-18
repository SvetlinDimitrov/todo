import styles from "./TaskContainer.module.css";
import Task from "../../../model/interfaces/objects/Task.ts";

interface TaskContainerProps {
  tasks: Task[];
  handleTaskClick: (taskId: number | null) => void;
}


function TaskContainer({tasks, handleTaskClick}: TaskContainerProps) {
  const bgColors = ['#FFD700', '#FF8C00', '#1E90FF', '#8A2BE2', '#A52A2A', '#20B2AA'];

  return (
    <>
      {(tasks.length !== 0 && tasks.every(t => t.done)) &&
        (<div className={styles.noTasks}>You have finish all your tasks :) </div>)
      }
      {(tasks.length !== 0) &&
        (<div className={styles.taskContainer}>
          {tasks.map((task, index) => (
            <span
              key={task.id}
              className={`${styles.taskDiv} ${task.done ? styles.taskCompleted : ''}`}
              style={{backgroundColor: task.done ? '' : bgColors[index % bgColors.length]}}
              onClick={() => handleTaskClick(task.id)}
            >
                #{task.name}
              </span>
          ))}
        </div>)
      }
    </>
  );
}

export default TaskContainer;