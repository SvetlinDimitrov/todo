import Task from "./Task.ts";

interface Project {
  id: number | null;
  name: string | null;
  tasks: Task[];
  userEmail: string | null;
}

export default Project;