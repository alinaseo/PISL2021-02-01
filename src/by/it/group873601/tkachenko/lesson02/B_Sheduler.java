package by.it.group873601.tkachenko.lesson02;

import java.util.*;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.
        List<Event> allevents= Arrays.asList(events);
        List<Event> bufferevents=new ArrayList<>();
        Collections.sort(allevents, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return Integer.valueOf(o1.start).compareTo(o2.start);
            }
        });
        int memory=from,first=0,second=0;
        for(int i=0;i<allevents.size();i++){
            if(first==0){
                if(allevents.get(i).start==memory)
                    bufferevents.add(allevents.get(i));
                else{
                    Collections.sort(bufferevents, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            return Integer.valueOf(o1.stop).compareTo(o2.stop);
                        }
                    });
                    result.add(bufferevents.get(0));
                    bufferevents=new ArrayList<>();
                    first=1;
                }
            }
            if(second==0&&first==1)
            {
                if(allevents.get(i).start>=result.get(result.size()-1).stop){
                    bufferevents.add(allevents.get(i));
                    second=1;
                    memory=allevents.get(i).start;
                }
            }
            else{
                if(second==1&&first==1){
                    if(allevents.get(i).start==memory){
                        bufferevents.add(allevents.get(i));
                    }else{
                        Collections.sort(bufferevents, new Comparator<Event>() {
                            @Override
                            public int compare(Event o1, Event o2) {
                                return Integer.valueOf(o1.stop).compareTo(o2.stop);
                            }
                        });
                        result.add(bufferevents.get(0));
                        bufferevents=new ArrayList<>();
                        second=0;
                        if(result.get(result.size()-1).stop>to||result.get(result.size()-1).start>to)
                            break;
                    }
                }
            }
        }







        return result;                        //вернем итог
    }
}