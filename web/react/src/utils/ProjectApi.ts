import axiosInstance from "../services/axios/axiosWrapper.ts";

export const getProjectById = async (id: string | undefined, auth: string | undefined) => {
  try {
    const response = await axiosInstance.get('/project/' + id,
      {
        headers: {'Authorization': `Basic ${auth}`}
      });
    return await response.data;
  } catch (error) {
    console.error('Failed to fetch tasks:', error);
  }
}

export const createProject = async (name: string | null, auth: string | undefined) => {
  try {
    const data = {name}
    await axiosInstance.post('/project', data,
      {
        headers: {'Authorization': `Basic ${auth}`}
      });
  } catch (error) {
    console.error('Failed to create project:', error);
  }
}

export const deleteProject = async (id: string | undefined, auth: string | undefined) => {
  try {
    await axiosInstance.delete('/project/' + id, {
      headers: {'Authorization': `Basic ${auth}`}
    });
  } catch (error) {
    console.error('Failed to delete project:', error);
  }
}

export const getProjects = async (auth: string | undefined) => {
  try {
    const response = await axiosInstance.get('/project',
      {
        headers: {'Authorization': `Basic ${auth}`}
      });
    return await response.data.content;
  } catch (error) {
    console.error('Failed to fetch projects:', error);
  }
}

export const updateProject = async (id: number, name: string | null, auth: string | undefined) => {
  try {
    const data = {name}
    await axiosInstance.put('/project/' + id, data,
      {
        headers: {'Authorization': `Basic ${auth}`}
      });
  } catch (error) {
    console.error('Failed to update project:', error);
  }
}