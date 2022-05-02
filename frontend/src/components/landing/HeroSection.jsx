import styled from "styled-components";
import Video from "../../assets/video.mp4";
import { Button, ButtonsKind } from "../common/Button";

const HeroContainer = styled.div`
  background: ${({ theme }) => theme.colors.secondary};
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 2rem;
  height: 800px;
  position: relative;
  z-index: 2;
`;

const HeroBg = styled.div`
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
`;

const Videobg = styled.video`
  width: 100%;
  height: 100%;
  -o-object-fit: cover;
  object-fit: cover;
  background: ${({ theme }) => theme.colors.secondary};
`;

const HeroContent = styled.div`
  z-index: 3;
  max-width: 1200px;
  position: absolute;
  padding: 8px 1.5rem;
  display: flex;
  flex-direction: column;
  align-items: center;
`;
const HeroH1 = styled.h1`
  color: ${({ theme }) => theme.colors.tertiary};
  font-size: 3rem;
  text-align: center;

  @media ${({ theme }) => theme.media.medium} {
    font-size: 2.5rem;
  }

  @media ${({ theme }) => theme.media.small} {
    font-size: 2rem;
  }
`;

const HeroP = styled.p`
  margin-top: 1.5rem;
  color: ${({ theme }) => theme.colors.tertiary};
  font-size: 1.5rem;
  text-align: center;
  max-width: 600px;

  @media ${({ theme }) => theme.media.medium} {
    font-size: 1.5rem;
  }

  @media ${({ theme }) => theme.media.small} {
    font-size: 1rem;
  }
`;
const HeroBtnWrapper = styled.div`
  margin-top: 4rem;
`;

function HeroSection() {
  return (
    <HeroContainer>
      <HeroBg>
        <Videobg autoPlay loop muted src={Video} type="video/mp4" />
      </HeroBg>
      <HeroContent>
        <HeroH1>
          CHECKLY - российская CRM-система автоматизации оценки персонала
        </HeroH1>
        <HeroP>
          Закрывайте вакансии быстрее с помощью CRM&nbsp;-&nbsp;системы CHECKLY
        </HeroP>
        <HeroBtnWrapper>
          <Button kind={ButtonsKind.secondary} to="/signin">
            Войти
          </Button>
        </HeroBtnWrapper>
      </HeroContent>
    </HeroContainer>
  );
}

export default HeroSection;
