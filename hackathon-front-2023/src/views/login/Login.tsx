import React, { useEffect, useState } from "react";
import { Checkbox, TextField, Typography } from "@mui/material";

// Alert
import { toast } from "react-toastify";

// Styles
import './Login.scss';
import Card from "../../components/UI/Card/Card";
import Layout from "../../components/UI/Layout/Layout";

import Button from "../../components/UI/Button/Button";

// Images
import logo from '../../assets/images/LogoLogo.png';


// Redux
// import { login } from "../store/features/auth";
import { getCookieFromBrowser } from "../../utils/AuthUtils";
import { useAppDispatch, useAppSelector } from "../../store/configs/hooks";
import { login } from "../../store/features/auth";
import {history} from '../../store/configs/mainStore';

const Login = () => {
  const dispatch = useAppDispatch();
  const [btnDisabled, setBtnDisabled] = useState(true)

  const handleDisable = (prop: any, value: any) => {
    setBtnDisabled(!value)
  }

  useEffect(() => {
    // Redireciona para a home se já está logado.
    if (getCookieFromBrowser("token")) {
      history.push("/");
    }
  });


  const [usuario, setUsuario] = useState("");
  const [senha, setSenha] = useState("");

  const logar = (e: any) => {
    e.preventDefault();
    if (usuario && senha) {
      dispatch(login({ usuario: usuario.trim(), senha: senha.trim() }));
    } else {
      toast.error("Preencha com usuário e senha para logar!", {
        position: "top-center",
      });
    }
  };

  return (
    <Layout headerInvisivel={true} tabbarInvisivel={true}>
      <div className="container">
        <div className="logoLogin">
          {/* <img src={logo} width={50} height={50} alt="Logo" />
          <div className="logoTitle">SysAgro</div> */}
          <img src={logo} height={50} alt="Logo" />
        </div>
        <Card className="cardLogin">

          <form className="inputsWrapper" onSubmit={logar}>
            <TextField
              className="textField"
              id="loginInput"
              label="Login"
              variant="outlined"
              value={usuario}
              onChange={(e) => {
                setUsuario(e.target.value);
              }}
            />
            <TextField
              className="textField"
              id="senhaInput"
              label="Senha"
              variant="outlined"
              value={senha}
              onChange={(e) => {
                setSenha(e.target.value);
              }}
              type="password"
            />
            <Button disabled={btnDisabled} className="botaoAcessar" type="submit">
              ACESSAR
            </Button>
            <Typography variant="caption" display="block" align="center">
              <Checkbox
                color="primary"
                value="checkedA"
                inputProps={{ "aria-label": "Checkbox A" }}
                onChange={handleDisable}
              />{" "}
              Declaro que li e estou de acordo
              <br /> com o decreto de lei LGPD
            </Typography>
          </form>
        </Card>
      </div>
    </Layout>
  );
};

export default Login;