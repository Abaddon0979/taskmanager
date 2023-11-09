import './App.css';
import AddTask from './components/AddTask';
import GetTask from './components/GetTask';
import GetAllTasks from './components/GetAllTasks';
import Home from './components/Home';
import Login from './components/Login';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthProvider'; // Import AuthProvider

function App() {
  return (
    <div>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/add-task" element={<AddTask />} />
            <Route path="/view-task" element={<GetTask />} />
            <Route path="/all-tasks" element={<GetAllTasks />} />
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;