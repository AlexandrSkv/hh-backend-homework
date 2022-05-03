import { useState } from 'react';
import styled from 'styled-components';
import { Link as LinkR } from 'react-router-dom';

const Container = styled.div`
    min-height: 693px;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    top: 0;
    z-index: 0;
    overflow: hidden;
    background: ${({ theme }) => theme.colors.secondary};
`;

const FormWrap = styled.div`
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;

    @media ${({ theme }) => theme.media.small} {
        height: 80%;
    }
`;

const Icon = styled(LinkR)`
    margin-left: 32px;
    margin-top: 32px;
    text-decoration: none;
    color: ${({ theme }) => theme.colors.tertiary};
    font-weight: 700;
    font-size: 32px;
`;

const FormContent = styled.div`
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;

    @media ${({ theme }) => theme.media.small} {
        padding: 10px;
    }
`;
const Form = styled.form`
    background: ${({ theme }) => theme.colors.tertiary};
    max-width: 400px;
    height: auto;
    width: 100%;
    z-index: 1;
    display: grid;
    margin: 0 auto;
    padding: 80px 32px;
    border-radius: 4px;

    @media ${({ theme }) => theme.media.small} {
        padding: 32px 32px;
    }
`;

const Form1 = styled.h1`
    margin-bottom: 40px;
    color: ${({ theme }) => theme.colors.secondary};
    font-size: 24px;
    font-weight: 400;
    text-align: center;
`;

const FormLabel = styled.label`
    margin-bottom: 8px;
    font-size: 14px;
    color: ${({ theme }) => theme.colors.secondary};
`;

const FormInput = styled.input`
    padding: 16px 16px;
    margin-bottom: 32px;
    border: none;
    border-radius: 4px;
`;

const FormButton = styled.button`
    background: ${({ theme }) => theme.colors.primary};
    padding: 16px 0;
    border: none;
    border-radius: 4px;
    color: ${({ theme }) => theme.colors.tertiary};
    font-size: 20px;
    cursor: pointer;
`;
const TextWrap = styled.div`
    display: flex;
    justify-content: space-around;
`;

const Text = styled.span`
    text-align: center;
    margin-top: 24px;
    color: ${({ theme }) => theme.colors.tertiary};
    font-size: 14px;
`;

function SigninElement() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };
    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        alert(`Вы ввели: \n 
            email: ${email} \n 
            пароль: ${password} \n
            Сделано для тестов`);
    };

    return (
        <Container>
            <FormWrap>
                <Icon to="/">CHECKLY</Icon>
                <FormContent>
                    <Form onSubmit={handleSubmit}>
                        <Form1>Войти в ваш кабинет</Form1>
                        <FormLabel htmlFor="for">Email</FormLabel>
                        <FormInput
                            type="email"
                            name="email"
                            placeholder="Введите почту"
                            onChange={handleEmailChange}
                            value={email}
                        />
                        <FormLabel htmlFor="for">Пароль</FormLabel>
                        <FormInput
                            type="password"
                            name="password"
                            placeholder="Введите пароль"
                            onChange={handlePasswordChange}
                            value={password}
                        />
                        <FormButton type="submit">Продолжить</FormButton>
                        <TextWrap>
                            <Text>Регистрация</Text>
                            <Text>Забыли пароль?</Text>
                        </TextWrap>
                    </Form>
                </FormContent>
            </FormWrap>
        </Container>
    );
}

export default SigninElement;
