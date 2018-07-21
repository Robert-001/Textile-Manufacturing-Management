import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';

import { Button, Form, FormGroup, Label, Input } from 'reactstrap';
import { SignUpLink } from './SignUp';
import { PasswordForgetLink } from './PasswordForget';
import { auth } from '../firebase';
import * as routes from '../constants/routes';

const SignInPage = ({ history }) =>
    <div style={{ margin: 50 }}>
        <h1>SignIn</h1>
        <SignInForm history={history} />
        <PasswordForgetLink />
    </div>

const byPropKey = (propertyName, value) => () => ({
    [propertyName]: value,
});

const INITIAL_STATE = {
    email: '',
    password: '',
    error: null,
};

class SignInForm extends Component {
    constructor(props) {
        super(props);

        this.state = { ...INITIAL_STATE };
    }

    onSubmit = (event) => {
        const {
            email,
            password,
        } = this.state;

        const {
            history,
        } = this.props;

        auth.doSignInWithEmailAndPassword(email, password)
            .then(() => {
                this.setState(() => ({ ...INITIAL_STATE }));
                history.push(routes.HOME);
            })
            .catch(error => {
                this.setState(byPropKey('error', error));
            });

        event.preventDefault();
    }

    render() {
        const {
            email,
            password,
            error,
        } = this.state;

        const isInvalid =
            password === '' ||
            email === '';

        return (
            <Form onSubmit={this.onSubmit}>
                <FormGroup>
                    <Label for="exampleEmail">Email</Label>
                    <Input value={email}
                        onChange={event => this.setState(byPropKey('email', event.target.value))}
                        placeholder="Email Address" type="email" name="email" id="exampleEmail" />
                </FormGroup>
                <FormGroup>
                    <Label for="examplePassword">Password</Label>
                    <Input value={password}
                        onChange={event => this.setState(byPropKey('password', event.target.value))} type="password" name="password" id="examplePassword" placeholder="Password" />
                </FormGroup>
                <Button disabled={isInvalid} type="submit">Submit</Button>
                {error && <p>{error.message}</p>}
            </Form>
        );
    }
}

export default withRouter(SignInPage);

export {
    SignInForm,
};