
import './App.css';
import AddTask from './components/AddTask';
import GetTask from './components/GetTask';
import GetAllTasks from './components/GetAllTasks';
import Index from './components/Index';
import Login from './components/Login';
import { BrowserRouter, Routes, Route } from 'react-router-dom';


function App() {
  return (
    <div>
     <BrowserRouter>
     <Routes>
      <Route path="/add-task" element={<AddTask />} />
      <Route path="/view-task" element={<GetTask />} />
      <Route path="/all-tasks" element={<GetAllTasks />} />
      <Route path="/index" element={<Index />} />
      <Route path="/login"element={<Login />} />
     </Routes>
     </BrowserRouter>
    </div>
  );
}

export default App;
