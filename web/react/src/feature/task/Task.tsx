import {useNavigate, useParams} from 'react-router-dom';
import {useContext, useEffect, useState} from "react";
import TaskObject from "../../model/interfaces/objects/Task.ts";
import styles from './Task.module.css';
import {UserContext} from "../../context/UserContext.tsx";
import Header from "../../model/header/Header.tsx";
import {deleteTaskById, getTaskById, updateTask} from "../../utils/TaskAPi.ts";
import Loader from "../../model/loader/Loader.tsx";
import TaskForm from "../../model/taskForm/TaskForm.tsx";
import {LoadingContext} from "../../context/LoadingContext.tsx";

function Task() {

  const {id: projectId, taskId} = useParams();
  const navigate = useNavigate();

  const userContext = useContext(UserContext);
  const loadingContext = useContext(LoadingContext);

  const [task, setTask] = useState<TaskObject>();
  const [showEditTaskPopup, setShowEditTaskPopup] = useState(false);

  useEffect(() => {
    const fetchTask = async () => {
      setTask(await getTaskById(projectId, taskId, userContext?.auth));
    }

    fetchTask();
  }, [userContext?.auth, projectId, taskId]);

  if (!task) {
    return <Loader/>;
  }

  async function deleteTaskAction() {
    const isConfirmed = window.confirm("Are you sure you want to delete this task?");

    if (!isConfirmed) {
      return;
    }

    await deleteTaskById(projectId, taskId, userContext?.auth);
    navigate(`/project/${projectId}`);
  }

  async function handleEditTaskAction(task: TaskObject) {
    try {
      loadingContext.setLoading(true);
      await updateTask(task, userContext?.auth);
      setTask(await getTaskById(projectId, taskId, userContext?.auth));
    } catch (e) {
      console.error(e);
    } finally {
      setShowEditTaskPopup(false);
      loadingContext.setLoading(false);
    }
  }

  function handleCloseTaskAction() {
    setShowEditTaskPopup(false);
  }

  return (
    <>
      <Header/>

      <div className={styles.taskContainer}>
        <h1 className={styles.taskTitle}>Task Details</h1>
        <p>Task Name: {task.name}</p>
        <div>
          {task.description &&
            <p className={styles.taskDescription}>Description: {task.description}</p>
          }
          <p className={`${styles.taskStatus} ${task.done ? '' : styles.pending}`}>
            Status: {task.done ? 'Completed' : 'Pending'}
          </p>
        </div>
        <div>
          <button
            className={styles.taskButton}
            onClick={() => setShowEditTaskPopup(true)}>
            ✏️
          </button>
          <button
            className={styles.taskButton}
            onClick={deleteTaskAction}>
            🗑️
          </button>
        </div>
      </div>

      {showEditTaskPopup &&
        <TaskForm
          headerText={"Edit Task"}
          buttonText={"Edit"}
          handleAction={handleEditTaskAction}
          handleClose={handleCloseTaskAction}
          task={task}/>
      }
    </>
  );
}

export default Task;