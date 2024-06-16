import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import {
  AppBar,
  Box,
  Button,
  Toolbar,
  Typography,
  CssBaseline,
  useScrollTrigger,
  Slide,
  Tooltip,
  IconButton,
  Grid,
  Menu,
  ListItemIcon,
  MenuItem,
  Avatar,
  Divider,
} from "@mui/material";
import { Link } from "react-router-dom";
import Logo from "../../assets/logoInversor.png";
import hamburguer from "../../assets/icon-hamburger.svg";
export const Header = (props) => {
  const pages = ["Home", "Blog", "Contact"];
  const [anchorElNav, setAnchorElNav] = useState(null);
  const [anchorElUser, setAnchorElUser] = useState(null);
  const [mobile, setMobile] = useState(window.innerWidth < 900);
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);

  const handleClick = (e) => {
    setAnchorEl(e.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleResize = () => {
    setMobile(window.innerWidth < 900);
  };

  useEffect(() => {
    window.addEventListener("resize", handleResize);
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  function HideOnScroll(props) {
    const { children } = props;
    const trigger = useScrollTrigger();

    return (
      <Slide appear={false} direction="down" in={!trigger}>
        {children}
      </Slide>
    );
  }

  HideOnScroll.propTypes = {
    children: PropTypes.element.isRequired,
  };

  return (
    <>
      {mobile ? (
        <>
          <CssBaseline />
          <HideOnScroll {...props}>
            <AppBar
              sx={{
                backgroundColor: "white",
                display: "flex",
                justifyContent: "space-between",
                width: "100%",
              }}
            >
              <Toolbar
                sx={{
                  backgroundColor: "white",
                  display: "flex",
                  justifyContent: "space-between",
                  width: "100%",
                  padding: mobile ? "0 20px" : "0 20px",
                  flexDirection: "row-reverse",
                }}
              >
                <img src={Logo} alt="logo" />

                <Box
                  sx={{
                    display: { xs: "none", md: "flex" },
                    height: "50px",
                    width: "50%",
                    justifyContent: "center",
                    alignItems: "center",
                    gap: "20px",
                  }}
                >
                  {pages.map((page) => (
                    <Link
                      key={page}
                      to={`/${page.toLowerCase()}`}
                      style={{
                        color: "grey",
                        textDecoration: "none",
                        fontSize: "20px",
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        height: "50%",
                      }}
                    >
                      {page}
                    </Link>
                  ))}
                </Box>

                <Grid>
                  <Tooltip title="Menu">
                    <IconButton
                      sx={{
                        ml: 2,
                        width: "40px",
                        height: "40px",
                        position: "relative",
                      }}
                      size="small"
                      aria-controls={open ? "account-menu" : undefined}
                      aria-haspopup="true"
                      aria-expanded={open ? "true" : undefined}
                      onClick={handleClick}
                    >
                      <img src={hamburguer} alt="menu" />
                    </IconButton>
                  </Tooltip>
                  <Menu
                    anchorEl={anchorEl}
                    id="account-menu"
                    open={open}
                    onClose={handleClose}
                    onClick={handleClose}
                    anchorOrigin={{ vertical: "bottom", horizontal: "left" }}
                    transformOrigin={{ vertical: "top", horizontal: "left" }}
                  >
                    <MenuItem onClick={handleClose}>Home</MenuItem>
                    <MenuItem onClick={handleClose}>Blog</MenuItem>
                    <Divider />
                    <MenuItem onClick={handleClose}>Contact</MenuItem>
                    <MenuItem onClick={handleClose}>Log Out</MenuItem>
                  </Menu>
                </Grid>
              </Toolbar>
            </AppBar>
          </HideOnScroll>
          <Toolbar />
        </>
      ) : (
        <>
          <CssBaseline />
          <HideOnScroll {...props}>
            <AppBar
              sx={{
                backgroundColor: "white",
                display: "flex",
                justifyContent: "space-between",
                width: "100%",
              }}
            >
              <Toolbar
                sx={{
                  backgroundColor: "white",
                  display: "flex",
                  justifyContent: "space-between",
                  width: "100%",
                  padding: mobile ? "0 50px" : "0 20px",
                }}
              >
                <img src={Logo} alt="logo" />

                <Box
                  sx={{
                    display: { xs: "none", md: "flex" },
                    height: "50px",
                    width: "50%",
                    justifyContent: "center",
                    alignItems: "center",
                    gap: "20px",
                  }}
                >
                  {pages.map((page) => (
                    <Link
                      key={page}
                      to={page.toLowerCase() === 'home' ? '/' : `/${page.toLowerCase()}`}
                      style={{
                        color: "grey",
                        textDecoration: "none",
                        fontSize: "20px",
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        height: "50%",
                      }}
                    >
                      {page}
                    </Link>
                  ))}
                </Box>
              </Toolbar>
            </AppBar>
          </HideOnScroll>
          <Toolbar />
        </>
      )}
    </>
  );
};

export default Header;
