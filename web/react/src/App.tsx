import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Home from "./feature/home/Home.tsx";
import Project from "./feature/project/Project.tsx";
import Task from "./feature/task/Task.tsx";
import NotFound from "./feature/errorPage/NotFound.tsx";

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/project/:id" element={<Project />}/>
        <Route path="/project/:id/task/:taskId" element={<Task />}/>
        <Route path="*" element={<NotFound/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;