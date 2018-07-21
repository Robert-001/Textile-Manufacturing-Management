import React, { Component } from 'react';

import { Jumbotron } from 'reactstrap';
import withAuthorization from './withAuthorization';
import { db } from '../firebase';

class HomePage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            users: null,
        };
    }

    componentDidMount() {
        db.onceGetUsers().then(snapshot =>
            this.setState(() => ({ users: snapshot.val() }))
        );
    }

    render() {
        const { users } = this.state;

        return (
            <div style={{ margin: 50 }}>
                <Jumbotron>
                    <h1 className="display-3">Home!</h1>
                    <p className="lead">The Home Page is accessible by every signed in user.</p>
                    <hr className="my-2" />
                    {!!users && <UserList users={users} />}
                </Jumbotron>                
            </div>
        );
    }
}

const UserList = ({ users }) =>
    <div style={{ margin: 50 }}>
        <h2>List of Usernames of Users</h2>
        <p>(Saved on Sign Up in Firebase Database)</p>

        {Object.keys(users).map(key =>
            <li><div key={key}>{users[key].username}</div></li>
        )}
    </div>

const authCondition = (authUser) => !!authUser;

export default withAuthorization(authCondition)(HomePage);