import styles from './Project.module.css';
import {useNavigate, useParams} from "react-router-dom";
import {useContext, useEffect, useState} from "react";
import ProjectHeader from "./projectHeader/ProjectHeader.tsx";
import TaskContainer from "./taskContainer/TaskContainer.tsx";
import {UserContext} from "../../context/UserContext.tsx";
import Header from "../../model/header/Header.tsx";
import {deleteProject, getProjectById} from "../../utils/ProjectApi.ts";
import {LoadingContext} from "../../context/LoadingContext.tsx";
import ProjectObject from "../../model/interfaces/objects/Project.ts";
import Loader from "../../model/loader/Loader.tsx";
import TaskForm from "../../model/taskForm/TaskForm.tsx";
import Task from "../../model/interfaces/objects/Task.ts";
import {createTask} from "../../utils/TaskAPi.ts";

const Project = () => {

  const {id} = useParams();
  const navigate = useNavigate();

  const userContext = useContext(UserContext);
  const loadingContext = useContext(LoadingContext);

  const [project, setProject] = useState<ProjectObject | undefined>();
  const [showAddTaskPopup, setShowAddTaskPopup] = useState(false);

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        loadingContext.setLoading(true);
        setProject(await getProjectById(id, userContext?.auth));
      } catch (e) {
        console.error(e);
      } finally {
        loadingContext.setLoading(false);
      }
    };

    fetchTasks();
  }, [userContext?.auth, id]);

  const handleDeleteProjectAction = async () => {
    const userAccepted = confirm("Do you want to delete it?");

    if (!userAccepted) {
      return;
    }

    try {
      loadingContext.setLoading(true);
      await deleteProject(id, userContext?.auth);
    } catch (e) {
      console.error(e);
    } finally {
      navigate("/");
      loadingContext.setLoading(false);
    }
  };

  async function handleAddTaskAction(task: Task) {
    const taskToCreate = {
      name: task.name,
      description: task.description,
      done: task.done ? 1 : 0,
    }
    try {
      loadingContext.setLoading(true);
      await createTask(id, taskToCreate, userContext?.auth);
      setProject(await getProjectById(id, userContext?.auth));
    } catch (e) {
      console.error(e);
    } finally {
      setShowAddTaskPopup(false);
      loadingContext.setLoading(false);
    }
  }

  const handleAddTaskCloseAction = () => {
    setShowAddTaskPopup(false);
  }

  const handleTaskClick = (taskId: number | null) => {
    navigate(`/project/${id}/task/${taskId}`);
  };

  if (!project) {
    return <Loader/>;
  }

  return (
    <>
      <Header/>
      <div className={styles.projectContainer}>
        <ProjectHeader
          handleTickClick={handleDeleteProjectAction}
          handleAddClick={() => setShowAddTaskPopup(true)}
          project={project}
        />
        <TaskContainer
          tasks={project.tasks}
          handleTaskClick={handleTaskClick}
        />
      </div>

      {showAddTaskPopup && (
        <TaskForm
          headerText={"Create Task"}
          buttonText={"Create"}
          handleAction={handleAddTaskAction}
          handleClose={handleAddTaskCloseAction}
          task={null}/>
      )}

    </>
  );
};

export default Project;