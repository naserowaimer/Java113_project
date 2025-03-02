# business & manufacturing event management system  

## Cover Sheet
- **Project Title**: business event management system
- **Course**: CSC113
- **Student**: Nasser Alowaymir
- **KSU ID number**: 445102817
- **Division number**: 77965

## Introduction  
the Business & Manufacturing Event Management System is Java program designed manage corporate events and industrial exhibitions. system features:  
- event scheduling for business conferences  
- venue management with capacity tracking  
- ticket sales with vip/general admission  
- sponsor management for partnerships  

build using OOP principles like:  
- inheritance between venues  
- composition (Venue-subvenue)  
- aggregation (sponsor-events)  
- polymorphic ticket displays  

## UML Diagram  
![system uml diagram](uml.png)  

## Implementation and Design Details  

### abstract Class venue  
- **Attributes**:  
  - venueid: String  
  - name: string  
  - capacity: int  
  - location: String  
  - events: Event[]  
- **Methods**:  
  - addevent(event: Event): boolean  
    - adds event to venue if capacity allows returns true if successful  
    - if events array full returns false  
  - removeevent(eventid: String): void  
    - deletes event with given id from events array  
    - if event not found do nothing  
  - searchevent(keyword: String): Event  
    - search events by title or id returns first match  
    - returns null if no match  

### Class Industrialcomplex (extends venue)  
- **Attributes**:  
  - factorytype: String  
- **Methods**:  
  - schedulesafetyinspection(): void  
    - adds safety inspection event to venue  
    - sets date to next week automatically  

### Class conferencecenter (extends Venue)  
- **Attributes**:  
  - meetingrooms: int  
- **Methods**:  
  - bookAVequipment(): void  
    - reserves AV equipment for all events in venue  
    - reduces available meetingrooms by 1  

### abstract Class event  
- **Attributes**:  
  - eventid: String  
  - date: String  
  - attendees: int  
- **Methods**:  
  - abstract calculaterevenue(): double  
    - calculate total revenue based on ticket sales  
  - abstract displayeventdetails(): void  
    - prints event info like date attendees  

### Class Businessevent (extends Event)  
- **Attributes**:  
  - industrysector: String  
  - keynotespeaker: String  
- **Methods**:  
  - calculaterevenue(): double  
    - revenue = attendees * 150 (standard business ticket price)  
  - displayeventdetails(): void  
    - prints industry sector and speaker name  

### Class Sponsor  
- **Attributes**:  
  - sponsorid: String  
  - contribution: double  
  - sponsoredevents: Event[]  
- **Methods**:  
  - sponsorevent(event: Event): void  
    - adds event to sponsoredevents array  
    - adds contribution amount to event budget  
  - cancelsponsorship(eventid: String): void  
    - removes event from sponsoredevents  
    - deducts contribution from event  

### Class Visitor  
- **Attributes**:  
  - visitorid: String  
  - tickets: Ticket[]  
- **Methods**:  
  - buyticket(event: Event): void  
    - creates new ticket adds to tickets array  
    - ticket price based on event type  
  - cancelticket(ticketid: String): void  
    - removes ticket from array if found  

## Source Code  
download source code: [eventsystem.zip](https://github.com/yourusername/CSC113-Project/phase1/source.zip  

## Sample Run Screenshot  
![sample run](sample_run.jpg)
