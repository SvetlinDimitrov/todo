interface Task {
  id: number | null;
  name: string | null;
  description: string | null;
  done: boolean | null;
  projectId: number | null;
}

export default Task;