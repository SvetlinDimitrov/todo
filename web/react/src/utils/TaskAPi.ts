import axiosInstance from "../services/axios/axiosWrapper.ts";
import Task from "../model/interfaces/objects/Task.ts";

export const getTaskById = async (projectId: string | undefined, taskId: string | undefined, auth: string | undefined) => {
  try {
    const response = await axiosInstance.get(`/task/${taskId}`, {
      params: { projectId: projectId },
      headers: { "Authorization": `Basic ${auth}` }
    });
    return response.data;
  } catch (error) {
    console.error('Failed to fetch task:', error);
  }
};

export const deleteTaskById = async (projectId: string | undefined, taskId: string | undefined, auth: string | undefined) => {
  try {
    await axiosInstance.delete(`/task/${taskId}`, {
      params: { projectId: projectId },
      headers: { "Authorization": `Basic ${auth}` }
    });
  } catch (error) {
    console.error('Failed to delete task:', error);
  }
}

export const updateTask = async (task: Task, auth: string | undefined) => {

  console.log(task);
  try {
    await axiosInstance.put(`/task/${task.id}`, task, {
      params: { projectId: task.projectId },
      headers: { "Authorization": `Basic ${auth}` }
    });
  } catch (error) {
    console.error('Failed to update task:', error);
  }
}

export const createTask = async (projectId: string | undefined, task: any, auth: string | undefined) => {
  try {
    await axiosInstance.post("/task", task, {
      params: { projectId: projectId },
      headers: { "Authorization": `Basic ${auth}` }
    });
  } catch (error) {
    console.error('Failed to create task:', error);
  }
}

