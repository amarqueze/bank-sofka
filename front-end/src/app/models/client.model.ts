import { Account } from "./account.model";

export interface Client {
    dni: string;
    name: string;
    lastName: string;
    address: string;
    phone: string;
    accounts: Account[];
}