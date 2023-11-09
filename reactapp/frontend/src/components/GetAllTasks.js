import React, { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Appbar from './Appbar';
import axios from 'axios';

const TaskList = () => {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        const token = sessionStorage.getItem('token'); // Get the token from localStorage

        // Set the Authorization header with the token
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

        axios.get('http://localhost:8080/api/all-tasks')
            .then((response) => {
                setTasks(response.data);
            })
            .catch((error) => {
                console.error('Error fetching tasks:', error);
            });
    }, []);

    return (
        <>
            <Appbar />
            <div>
                <Box sx={{ display: 'flex', justifyContent: 'center', marginTop: '20px' }}>
                    {tasks.map(task => (
                        <Paper key={task.id} sx={{ padding: '20px', width: '300px', margin: '10px' }}>
                            <h2>Task ID: {task.id}</h2>
                            <div>
                                <p><strong>Title:</strong> {task.title}</p>
                            </div>
                            <div>
                                <p><strong>Description:</strong> {task.description}</p>
                            </div>
                            <div>
                                <p><strong>Due Date:</strong> {task.dueDate}</p>
                            </div>
                            <div>
                                <p><strong>Done Status:</strong> {task.done ? 'Done' : 'Not Done'}</p>
                            </div>
                        </Paper>
                    ))}
                </Box>
            </div>
        </>
    );
};

export default TaskList;