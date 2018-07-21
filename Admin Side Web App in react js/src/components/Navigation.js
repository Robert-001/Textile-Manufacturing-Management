import React from 'react';
import { Link } from 'react-router-dom';
import {
    Navbar,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink
} from 'reactstrap';
import AuthUserContext from './AuthUserContext';
import SignOutButton from './SignOut';
import * as routes from '../constants/routes';

const Navigation = () =>
    <AuthUserContext.Consumer>
        {authUser => authUser
            ? <NavigationAuth />
            : <NavigationNonAuth />
        }
    </AuthUserContext.Consumer>

const NavigationAuth = () =>
    <div>
        <Navbar color="light" light expand="md">
            <NavbarBrand><h3>Textile Manufacturing Management App</h3></NavbarBrand>
            <Nav className="ml-auto" navbar>
                <NavItem>
                    <NavLink><Link to={routes.LANDING}>Landing</Link></NavLink>
                </NavItem>
                <NavItem>
                    <NavLink><Link to={routes.HOME}>Home</Link></NavLink>
                </NavItem>
                <NavItem>
                    <NavLink><Link to={routes.SIGN_UP}>Create new User</Link></NavLink>
                </NavItem>
                <NavItem>
                    <NavLink><Link to={routes.ACCOUNT}>User Management</Link></NavLink>
                </NavItem>
                <NavItem>
                    <NavLink><SignOutButton /></NavLink>
                </NavItem>
            </Nav>
        </Navbar>
    </div>

const NavigationNonAuth = () =>
    <div>
        <Navbar color="light" light expand="md">
            <NavbarBrand><h3>Textile Manufacturing Management App</h3></NavbarBrand>
            <Nav className="ml-auto" navbar>
                <NavItem>
                    <NavLink><Link to={routes.LANDING}>Landing</Link></NavLink>
                </NavItem>
                <NavItem>
                    <NavLink><Link to={routes.SIGN_IN}>Sign In</Link></NavLink>
                </NavItem>
            </Nav>
        </Navbar>
    </div>


export default Navigation;