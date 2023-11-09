import { useRef, useState, useEffect, useContext } from 'react';
import AuthContext from '../context/AuthProvider';
import axios from 'axios';
import Paper from '@mui/material/Paper';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

const LOGIN_URL = '/api/auth/signin';

const Login = () => {
  const { setAuth } = useContext(AuthContext);
  const userRef = useRef();
  const errRef = useRef();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errMsg, setErrMsg] = useState('');
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    if (userRef.current) {
      userRef.current.focus();
    }
  }, []);

  useEffect(() => {
    setErrMsg('');
  }, [username, password]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        LOGIN_URL,
        JSON.stringify({ username, password }),
        {
          headers: { 'Content-Type': 'application/json' },
          withCredentials: true,
        }
      );
      const accessToken = response?.data?.token;
      const role = response?.data?.role;

      console.log(accessToken);

      setAuth({ username, password, role, accessToken });
      sessionStorage.setItem('token', accessToken);
      setUsername('');
      setPassword('');
      setSuccess(true);
    } catch (err) {
      if (!err?.response) {
        setErrMsg('No Server Response!');
      } else if (err.response?.status === 400) {
        setErrMsg('Missing Username or Password!');
      } else if (err.response?.status === 401) {
        setErrMsg('Unauthorized!');
      } else {
        setErrMsg('Login failed!');
      }
      if (errRef.current) {
        errRef.current.focus();
      }
    }
  };

  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
      <Paper style={{ padding: '20px', maxWidth: '400px', width: '100%' }}>
        {success ? (
          <section>
            <h1>You are logged in!</h1>
            <br />
            <p>
              <a href="/">Go to homepage</a>
            </p>
          </section>
        ) : (
          <section>
            <p
              ref={errRef}
              style={{ color: 'red', marginBottom: '10px', textAlign: 'center' }}
              aria-live="assertive"
            >
              {errMsg}
            </p>
            <h1 style={{ color: 'blue', textAlign: 'center', marginBottom: '20px' }}>Sign In</h1>
            <form onSubmit={handleSubmit} style={{ textAlign: 'center' }}>
              <div style={{ marginBottom: '20px' }}>
                <TextField
                  fullWidth
                  label="Username"
                  variant="outlined"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                  inputRef={userRef}
                />
              </div>
              <div style={{ marginBottom: '20px' }}>
                <TextField
                  fullWidth
                  label="Password"
                  variant="outlined"
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              <Button variant="contained" type="submit" fullWidth style={{ backgroundColor: '#1976D2', color: 'white' }}>
                Sign In
              </Button>
            </form>
          </section>
        )}
      </Paper>
    </div>
  );
};

export default Login;