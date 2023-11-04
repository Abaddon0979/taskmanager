import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container, Paper, Button } from '@mui/material';
import { useState } from 'react';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';


export default function AddTask() {
    const paperStyle={padding: '20px 20px', width: 600, margin: '20px auto'}
    const [taskID, setTaskID]=useState('')
    const [title, setTitle]=useState('')
    const [description, setDescription]=useState('')
    const handleClick=(e)=>{
      e.preventDefault()
      const Task = {taskID, title, description}
      console.log(Task)
    }

  return (
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
            <h1 style={{color:"blue"}}><u>Add Task</u></h1>
            
            <TextField id="outlined-basic" label="Task ID" variant="outlined" fullWidth
            value = {taskID}
            onChange={(e)=>setTaskID(e.target.value)}
            />

            <TextField id="outlined-basic" label="Task Title" variant="outlined" fullWidth
            value = {title}
            onChange={(e)=>setTitle(e.target.value)}
            />
            
            <TextField id="outlined-basic" label="Task Description" variant="outlined" fullWidth
            value = {description}
            onChange={(e)=>setDescription(e.target.value)}
            />

            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DemoContainer components={['DatePicker']}>
                <DatePicker label="Task due date" slotProps={{ textField: { fullWidth: true }}}/>
              </DemoContainer>
            </LocalizationProvider>

            <div style={{ display: 'flex', justifyContent: 'center' }}>
              <FormControlLabel control={<Checkbox />} label="Task is done" />
            </div>

            <Button variant="contained"  onClick={handleClick}>Submit</Button>


            </Paper>
        </Container>
      

    </Box>
  );
}
