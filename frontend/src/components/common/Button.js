import styled from 'styled-components';
import { Link as LinkR } from 'react-router-dom';

export const ButtonWrap = styled.div`
    display: flex;
    justify-content: flex-start;
`;

export const BtnWrap = styled.div`
    display: flex;
    justify-content: flex-start;
`;

export const ButtonsKind = {
    primary: 'primary',
    secondary: 'secondary'
};

const ButtonsProps = {
    [ButtonsKind.primary]: {
        color: 'tertiary',
        background: 'secondary',
        hoverBackground: 'primary'
    },
    [ButtonsKind.secondary]: {
        color: 'secondary',
        background: 'tertiary',
        hoverBackground: 'primary'
    }
};

export const Button = styled(LinkR)`
    border-radius: 50px;
    background: ${({ theme, kind = ButtonsKind.primary }) =>
        theme.colors[ButtonsProps[kind].background]};
    white-space: nowrap;
    padding: 1rem 4rem;
    color: ${({ theme, kind = ButtonsKind.primary }) =>
        theme.colors[ButtonsProps[kind].color]};
    font-size: 1rem;
    outline: none;
    border: none;
    cursor: pointer;
    transition: all 0.2s ease-in-out;
    text-decoration: none;
    &:hover {
        transition: all 0.2s ease-in-out;
        background: ${({ theme, kind = ButtonsKind.primary }) =>
        theme.colors[ButtonsProps[kind].hoverBackground]};
        color: ${({ theme, kind = ButtonsKind.primary }) =>
        theme.colors[ButtonsProps[kind].color]};
    }
`;
