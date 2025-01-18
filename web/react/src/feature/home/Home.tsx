import {useNavigate} from "react-router-dom";
import styles from './Home.module.css';
import {useContext, useEffect, useState} from "react";
import Project from "../../model/interfaces/objects/Project.ts";
import {UserContext} from "../../context/UserContext";
import {createProject, getProjects, updateProject} from "../../utils/ProjectApi.ts";
import Header from "../../model/header/Header.tsx";
import {LoadingContext} from "../../context/LoadingContext.tsx";
import ProjectForm from "../../model/projectForm/ProjectForm.tsx";

function Home() {

  const navigate = useNavigate();

  const userContext = useContext(UserContext);
  const loadingContext = useContext(LoadingContext);

  const [addProjectPopUp, setAddProjectPopUp] = useState<boolean>(false);
  const [editProjectPopUp, setEditProjectPopUp] = useState<boolean>(false);
  const [projects, setProjects] = useState<Project[]>([]);
  const [project, setProject] = useState<Project>();

  useEffect(() => {

    const fetchProjects = async () => {
      try {
        loadingContext?.setLoading(true);
        setProjects(await getProjects(userContext?.auth));
      } catch (e) {
        console.log(e);
      } finally {
        loadingContext?.setLoading(false);
      }
    };

    fetchProjects();
  }, []);

  const handleProjectClick = (project: Project) => {
    navigate('project/' + project.id);
  }

  const showEditProjectPopUp = (project: Project) => {
    setProject(project);
    setEditProjectPopUp(true);
  }

  const showAddProjectPopUp = () => {
    setAddProjectPopUp(true);
  }

  async function handleAddProject(projectName: string) {
    try {
      loadingContext.setLoading(true);
      await createProject(projectName, userContext?.auth);
      setProjects(await getProjects(userContext?.auth));
    } catch (e) {
      console.error(e);
    } finally {
      setAddProjectPopUp(false);
      loadingContext.setLoading(false);
    }
  }

  async function handleEditProject(projectName: string) {
    if (project && project.id)
      try {
        loadingContext.setLoading(true);
        await updateProject(project.id, projectName, userContext?.auth);
        setProjects(await getProjects(userContext?.auth));
      } catch (e) {
        console.error(e);
      } finally {
        setEditProjectPopUp(false);
        loadingContext.setLoading(false);
      }
  }

  return (
    <>
      <Header/>
      <div className={styles.homeContainer}>
        <div className={styles.container2}>
          <h1 className={styles.title}>Projects</h1>
          <button className={styles.addButton} onClick={() => showAddProjectPopUp()}>+</button>
        </div>
        <div className={styles.borderDiv}>
          {projects.map((project) => (
            <div key={project.id} className={styles.projectItem}>
              <span onClick={() => handleProjectClick(project)}
                    className={styles.projectTitle}>
                {project.name}
              </span>
              <button className={styles.pencilButton} onClick={() => showEditProjectPopUp(project)}>
                <span className={styles.pencilIcon}>✏️</span>
              </button>
            </div>
          ))}
        </div>
      </div>

      {addProjectPopUp &&
        <ProjectForm
          headerText={"Create Project"}
          buttonText={"Create"}
          handleAction={handleAddProject}
          handleClose={() => setAddProjectPopUp(false)}
          project={{name: null, id: null , tasks: [], userEmail: null}}
        />
      }

      {project && editProjectPopUp &&
        <ProjectForm
          headerText={"Edit Project"}
          buttonText={"Edit"}
          handleAction={handleEditProject}
          handleClose={() => setEditProjectPopUp(false)}
          project={project}
        />
      }
    </>
  );
}

export default Home;