import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Paper, Button } from '@mui/material';
import { useState } from 'react';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import Appbar from './Appbar';
import axios from 'axios';

export default function GetTask() {
    const paperStyle = { padding: '20px 20px', width: 600, margin: '20px auto' };
    const [id, setTaskID] = useState('');
    const [task, setTask] = useState(null);
    const [isTaskIDNumeric, setIsTaskIDNumeric] = useState(true);
    const [selectedDate, setSelectedDate] = useState(null);
    const [changesMade, setChangesMade] = useState(false);
    const [modifyClicked, setModifyClicked] = useState(false);
    const [tempTitle, setTempTitle] = useState('');
    const [tempDescription, setTempDescription] = useState('');
    const [submitClicked, setSubmitClicked] = useState(false);

    const handleDateChange = (date) => {
        setSelectedDate(date);
        setChangesMade(true);
    };

    const handleClick = async (e) => {
        e.preventDefault();
        if (isTaskIDNumeric) {
            try {
                const response = await axios.get(`http://localhost:8080/api/task?taskID=${id}`);
                setTask(response.data);
                setSubmitClicked(true);
            } catch (error) {
                console.error('Error fetching task:', error);
            }
        }
    }

    const handleTitleChange = (e) => {
        setTempTitle(e.target.value);
        setChangesMade(true);
    }

    const handleDescriptionChange = (e) => {
        setTempDescription(e.target.value);
        setChangesMade(true);
    }

    const handleSaveChanges = async () => {
        try {
            if (tempTitle !== null) {
                const titleResponse = await axios.post(`http://localhost:8080/api/modify-title?taskID=${task.id}`, tempTitle, {
                    headers: {
                        'Content-Type': 'text/plain'
                    }
                });
                setTask(prevTask => ({
                    ...prevTask,
                    title: tempTitle
                }))
                console.log(titleResponse.data);
            }
    
            if (tempDescription !== null) {
                const descriptionResponse = await axios.post(`http://localhost:8080/api/modify-description?taskID=${task.id}`, tempDescription, {
                    headers: {
                        'Content-Type': 'text/plain'
                    }
                });
                setTask(prevTask => ({
                    ...prevTask,
                    description: tempDescription
                }))
                console.log(descriptionResponse.data);
            }
    
            if (selectedDate !== null) {
                const formattedDate = selectedDate.format('YYYY-MM-DD');
                console.log(formattedDate)
                const dateResponse = await axios.post(`http://localhost:8080/api/modify-date?taskID=${task.id}`, formattedDate, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
                setTask(prevTask => ({
                    ...prevTask,
                    dueDate: formattedDate
                }))
                console.log(dateResponse.data);
            }
    
            setChangesMade(false);
        } catch (error) {
            console.error('Error updating task:', error);
        }
    }

    const handleToggleDoneStatus = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/change-status?taskID=${task.id}`);
            setTask(prevTask => ({
                ...prevTask,
                done: !prevTask.done
            }));
            console.log(response.data);
        } catch (error) {
            console.error('Error toggling done status:', error);
        }
    };

    const handleModifyClicked = () => {
        setModifyClicked(true);
    }

    const handleCancelModify = () => {
        setModifyClicked(false);
    }

    const handleDeleteTask = async () => {
        const confirmDelete = window.confirm(`Are you sure you want to delete task ${task.id}?`);
        if (confirmDelete) {
            try {
                await axios.delete(`http://localhost:8080/api/delete?taskID=${task.id}`);
                setTask(null);
                resetForm();
                alert(`Task ${task.id} has been successfully deleted`);
            } catch (error) {
                console.error('Error deleting task:', error);
            }
        }
    };

    const resetForm = () => {
        setModifyClicked(false);
        setSubmitClicked(false);
        setTempTitle('');
        setTempDescription('');
        setSelectedDate(null);
        setChangesMade(false);
        setIsTaskIDNumeric(true);
        setTaskID('');
    }

    return (
        <>
            <Appbar />
            <Box
                component="form"
                sx={{
                    left: "50%",
                }}
                noValidate
                autoComplete="off"
            >
                <Container>
                    <Paper elevation={3} style={paperStyle}>
                        <h1 style={{ color: "blue" }}><u>Search for task</u></h1>

                        <TextField
                            id="outlined-basic"
                            label="Task ID"
                            variant="outlined"
                            fullWidth
                            value={id}
                            onChange={(e) => {
                                const value = e.target.value;
                                setTaskID(value);
                                setIsTaskIDNumeric(/^\d+$/.test(value));
                            }}
                            onBlur={() => {
                                setIsTaskIDNumeric(/^\d+$/.test(id));
                            }}
                            error={!isTaskIDNumeric}
                            helperText={!isTaskIDNumeric && 'Please enter a numeric value'}
                            sx={{ mb: 1 }}
                        />

                        <Button variant="contained" onClick={handleClick}>Submit</Button>

                        {task && (
                            <div>
                                <h2>Task ID: {task.id}</h2>
                                <div style={{ marginBottom: '8px' }}>
                                    <p><strong>Title:</strong> {task.title}</p>
                                </div>
                                <div style={{ marginBottom: '8px' }}>
                                    <p><strong>Description:</strong> {task.description}</p>
                                </div>
                                <div style={{ marginBottom: '8px' }}>
                                    <p><strong>Due Date:</strong> {task.dueDate}</p>
                                </div>
                                <div style={{ marginBottom: '8px' }}>
                                    <p><strong>Done Status:</strong> {task.done ? 'Done' : 'Not Done'}</p>
                                    <Button variant="contained" onClick={handleToggleDoneStatus} style={{ height: '40px' }}>
                                        {task.done ? 'Set as Not Done' : 'Set as Done'}
                                    </Button>
                                </div>
                            </div>
                        )}

                        {modifyClicked && (
                            <div>
                                <div style={{ marginBottom: '8px' }}>
                                    <p><strong>New Title:</strong></p>
                                    <TextField variant="outlined" placeholder="Leave empty if no change" value={tempTitle} onChange={handleTitleChange} fullWidth />
                                </div>
                                <div style={{ marginBottom: '8px' }}>
                                    <p><strong>New Description:</strong></p>
                                    <TextField variant="outlined" placeholder="Leave empty if no change" value={tempDescription} onChange={handleDescriptionChange} fullWidth />
                                </div>
                                <div style={{ marginBottom: '8px' }}>
                                    <p><strong>New Due Date (leave empty if no change):</strong></p>
                                    <div style={{ display: 'inline-block' }}>
                                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                                            <DatePicker
                                                label="New due date"
                                                value={selectedDate}
                                                onChange={handleDateChange}
                                                renderInput={(params) => <TextField {...params} fullWidth variant="outlined" placeholder="Leave empty if no change" />}
                                            />
                                        </LocalizationProvider>
                                    </div>
                                </div>
                                <div style={{ display: 'flex', alignItems: 'center' }}>
                                    <Button variant="contained" onClick={handleSaveChanges} style={{ height: '40px', backgroundColor: changesMade ? '#1976D2' : '#ccc', pointerEvents: changesMade ? 'auto' : 'none', marginRight: '8px' }}>
                                        Save Changes
                                    </Button>
                                    <Button variant="contained" onClick={handleCancelModify} style={{ height: '40px', backgroundColor: '#FF0000' }}>
                                        Cancel Modify
                                    </Button>
                                </div>
                            </div>
                        )}
                        <div style={{ display: 'flex', marginTop: '8px' }}>
                            {submitClicked && !modifyClicked && (
                                <Button variant="contained" onClick={handleModifyClicked} style={{ marginRight: '8px' }}>
                                    Modify Task Info
                                </Button>
                            )}
                            {task && (
                                <Button variant="contained" onClick={handleDeleteTask} style={{ backgroundColor: '#FF0000' }}>
                                    Delete Task
                                </Button>
                            )}
                        </div>
                    </Paper>
                </Container>
            </Box>
        </>
    );
}