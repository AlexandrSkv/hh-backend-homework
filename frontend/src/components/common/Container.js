import styled from "styled-components";

export const Container = styled.div`
  color: ${({ theme }) => theme.colors.secondary};
  background: ${({ theme }) => theme.colors.tertiary};
  padding: 4rem 0;
`;
