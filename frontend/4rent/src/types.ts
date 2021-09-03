export type RealStateDTO = {
    id: number
    announcementTitle: string,
    announcementDate: Date,
    city: string,
    state: string,
    price: number,
    forRent: boolean,
    status: string,
    type: string
}

export type LandDetail = {
    announcementDate: string,
    announcementTitle: string,
    area: number,
    city: string,
    forRent: boolean,
    id: number,
    neighborhood: string,
    number: number,
    ownerName: string,
    price: number,
    state: string,
    status: string,
    street: string,
    zipCode: string,
}

export type HouseDetail = {
    announcementDate: string,
    announcementTitle: string,
    area: number,
    builtArea: number,
    city: string,
    floor: number,
    forRent: boolean,
    id: number,
    neighborhood: string,
    number: number,
    ownerName: string,
    price: number,
    roomsQuant: number,
    state: string,
    status: string,
    street: string,
    zipCode: string
}

export type ApartmentDetail = {
    announcementDate: string,
    announcementTitle: string,
    area: number,
    builtArea: number,
    city: string,
    floor: number,
    forRent: boolean,
    id: number,
    neighborhood: string,
    number: number,
    ownerName: string,
    price: number,
    roomsQuant: number,
    state: string,
    status: string,
    street: string,
    zipCode: string,
    aptArea: number,
    condomValue: number,
    garageSpots: number,
}

export type ApartmentForm = {
    announcementTitle: string,
    aptArea: number,
    area: number,
    city: string,
    condomValue: number,
    floor: number,
    forRent: boolean,
    garageSpots: number,
    neighborhood: string,
    number:	number,
    ownerEmail:	string,
    price:	number,
    roomsQuant:	number,
    state:	string,
    street:	string,
    zipCode: string
}

export type HouseForm = {
    announcementTitle: string,
    area: number,
    city: string,
    floor: number,
    forRent: boolean,
    neighborhood: string,
    number:	number,
    ownerEmail:	string,
    price:	number,
    roomsQuant:	number,
    state:	string,
    street:	string,
    zipCode: string,
    builtArea: number
}

export type LandForm = {
    announcementTitle: string,
    area: number,
    city: string,
    forRent: boolean,
    neighborhood: string,
    number:	number,
    ownerEmail:	string,
    price:	number,
    state:	string,
    street:	string,
    zipCode: string,
}

export const emptyLandForm = {
    announcementTitle: "",
    area: 0,
    city: "",
    forRent: false,
    neighborhood: "",
    number:	0,
    ownerEmail:	"",
    price:	0,
    state:	"",
    street:	"",
    zipCode: "",
}

export const emptyHouseForm = {
    announcementTitle: "",
    area: 0,
    city: "",
    floor: 0,
    forRent: false,
    neighborhood: "",
    number:	0,
    ownerEmail:	"",
    price:	0,
    roomsQuant:	0,
    state:	"",
    street:	"",
    zipCode: "",
    builtArea: 0
}

export const emptyApartmentForm = {
    announcementTitle: "",
    aptArea: 0,
    area: 0,
    city: "",
    condomValue: 0,
    floor: 0,
    forRent: false,
    garageSpots: 0,
    neighborhood: "",
    number:	0,
    ownerEmail:	"",
    price:	0,
    roomsQuant:	0,
    state:	"",
    street:	"",
    zipCode: ""
}
