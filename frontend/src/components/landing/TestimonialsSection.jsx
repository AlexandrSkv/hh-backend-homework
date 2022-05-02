import styled from "styled-components";
// eslint-disable-next-line import/no-unresolved
import "swiper/css";
// eslint-disable-next-line import/no-unresolved
import "swiper/css/pagination";
import { Pagination } from "swiper";
// eslint-disable-next-line import/no-unresolved
import { Swiper, SwiperSlide } from "swiper/react";
import { Button, ButtonsKind, ButtonWrap } from "../common/Button";
import { Heading, Subtitle } from "../common/Text";
import AVTR from "../../assets/AVTR.svg";

const TestimonialsContainer = styled.div`
  background: ${({ theme }) => theme.colors.tertiary};
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  z-index: 2;
  overflow: hidden;
  padding: 4rem 0;
`;

const TestimonialsContent = styled.div`
  max-width: 1200px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const TestimonialsSwiper = styled(Swiper)`
  width: 60%;
  overflow: hidden;
  margin-bottom: 2rem;

  @media ${({ theme }) => theme.media.medium} {
    width: 40%;
  }

  @media ${({ theme }) => theme.media.small} {
    width: 30%;
  }
`;

const SwiperWrap = styled(SwiperSlide)`
  background: ${({ theme }) => theme.colors.tertiary};
  text-align: center;
  padding: 2rem 0;
  border-radius: 2rem;
  user-select: none;
`;

const Avatar = styled.div`
  width: 8rem;
  aspect-ratio: 1/1;
  overflow: hidden;
  border-radius: 50%;
  margin: 0 auto 1rem;
  border: 0.4rem solid #72a8ee;
`;

const Img = styled.img`
  width: 100%;
`;

const Name = styled.h5`
  color: ${({ theme }) => theme.colors.secondary};
  font-weight: 300;
  display: block;
  width: 80%;
  margin: 0 auto 0;
  font-size: 1.5rem;
`;

const TESTIMONIALS_LIST = [
  {
    name: "Старовойтов Денис",
    icon: AVTR,
    text: "Все понравилось. И персонал, и месторождение, и печеньки с кофе, и чистота в помещениях. В серверной лишь нужно в порядок привести стол с монитором и ещё один стул добавить. Молодцы. Уходить не хотелось",
  },
  {
    name: "Мосунов Александр",
    icon: AVTR,
    text: "Очень приятно работать с этой компанией. Во-первых, специалисты тех.поддержки и уже на месте(сдавал сервер на размещение) работают вежливые и грамотные спецы... А вот заявления менеджеров делите на 5, а то и на 10...",
  },
  {
    name: "Иванов Семен",
    icon: AVTR,
    text: "Все понравилось. И персонал, и месторождение, и печеньки с кофе, и чистота в помещениях. В серверной лишь нужно в порядок привести стол с монитором и ещё один стул добавить. Молодцы. Уходить не хотелось",
  },
];

function TestimonialsSection() {
  return (
    <TestimonialsContainer id="testimonials">
      <TestimonialsContent>
        <Heading>Отзывы клиентов</Heading>
        <TestimonialsSwiper
          modules={[Pagination]}
          spaceBetween={40}
          slidesPerView={1}
          pagination={{ clickable: true }}
        >
          {TESTIMONIALS_LIST.map((testimonials) => (
            <SwiperSlide>
              <SwiperWrap>
                <Avatar>
                  <Img src={testimonials.icon} alt="carr" />
                </Avatar>
                <Name>{testimonials.name}</Name>
                <Subtitle>{testimonials.text}</Subtitle>
              </SwiperWrap>
            </SwiperSlide>
          ))}
        </TestimonialsSwiper>
        <ButtonWrap>
          <Button
            kind={ButtonsKind.primary}
            to=""
            // eslint-disable-next-line no-return-assign
            onClick={() => (window.location = "mailto:smat11@mail.ru")}
          >
            Оставить отзыв
          </Button>
        </ButtonWrap>
      </TestimonialsContent>
    </TestimonialsContainer>
  );
}

export default TestimonialsSection;
