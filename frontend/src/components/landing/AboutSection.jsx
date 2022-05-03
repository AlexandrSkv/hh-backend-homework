import Rec from '../../assets/Rec.svg';
import { Button, ButtonsKind, ButtonWrap } from '../common/Button';
import { Img, ImgWrap } from '../common/Img';
import { Container, Wrapper, Row, Column1, Column2 } from '../common/Column';
import { TextWrapper, TopLine, Heading, Subtitle } from '../common/Text';

function AboutSection() {
    return (
        <Container id="about">
            <Wrapper>
                <Row>
                    <Column1>
                        <TextWrapper>
                            <TopLine>CHECKLY</TopLine>
                            <Heading>Автоматизация поиска сотрудников</Heading>
                            <Subtitle>
                                CHECKLY — российская компания
                                интернет-рекрутмента, развивающая бизнес в
                                России. Клиентами Checkly являются множество
                                компаний. Вы можете держать обширную базу
                                соискателей. hh.ru содержит более чем 50 млн
                                резюме, а среднее дневное количество вакансий
                                превышает 691 тыс. и ведь их всех нужно
                                проверить
                            </Subtitle>
                            <ButtonWrap>
                                <Button kind={ButtonsKind.primary} to="/signin">
                                    Начать
                                </Button>
                            </ButtonWrap>
                        </TextWrapper>
                    </Column1>
                    <Column2>
                        <ImgWrap>
                            <Img src={Rec} alt="Rec" />
                        </ImgWrap>
                    </Column2>
                </Row>
            </Wrapper>
        </Container>
    );
}

export default AboutSection;
